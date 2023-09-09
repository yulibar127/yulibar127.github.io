const jwt = require('jsonwebtoken');
const Chat = require('../models/Chat');
const User = require('../models/User');

exports.getUserChats = (req, res) => {
  const bearerHeader = req.get('Authorization');
  if (!bearerHeader) {
    return res.status(401).json({ error: 'No token provided.' });
  }

  const token = bearerHeader.split(' ')[1]; // Get token from 'Bearer token'

  try {
    // Verify the JWT token and decode it
    const decoded = jwt.verify(token, 'your-secret-key');

    // Find all chats where the user's username appears in the chat's users array
    Chat.find({ 'users.username': decoded.username })
      .populate({
        path: 'users',
        match: { username: { $ne: decoded.username } },
        select: 'username displayName profilePic',
        options: { limit: 1 }
      }) // Populate the 'users' field with specific fields and exclude the decoded user
      .then(chats => {
        const response = chats.map(chat => {
          let user = '';
          if(decoded.username == chat.users[0].username)
          {
            user = chat.users[1];
          }
          else
          {
            user = chat.users[0];
          }
          return {
            id: chat.id,
            user: user ? user : null, // Return the user if it exists, otherwise null
            lastMessage: chat.messages.length > 0 ? chat.messages[chat.messages.length - 1] : null
          };
        });

        return res.status(200).json(response);
      })
      .catch(error => {
        return res.status(500).json({ error: 'Failed to get user chats. Error: ' + error.message });
      });
  } catch (err) {
    // If the token is not valid, return an unauthorized error
    return res.status(401).json({ error: 'Invalid token.' });
  }
};
