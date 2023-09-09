const jwt = require('jsonwebtoken');
const Chat = require('../models/Chat');

exports.deleteChatById = (req, res) => {
  const bearerHeader = req.get('Authorization');
  if (!bearerHeader) {
    return res.status(401).json({ error: 'No token provided.' });
  }

  const token = bearerHeader.split(' ')[1]; // Get token from 'Bearer token'
  const chatId = req.params.id; // Get chat ID from the request URL parameter

  try {
    // Verify the JWT token
    const decoded = jwt.verify(token, 'your-secret-key');

    // Find the chat based on the provided ID
    Chat.findOne({ id: chatId })
      .then(chat => {
        if (!chat) {
          return res.status(404).json({ error: 'Chat not found.' });
        }

        // Check if the token's username is included in the chat's users
        const tokenUsername = decoded.username;
        const isUserInChat = chat.users.some(user => user.username === tokenUsername);
        if (!isUserInChat) {
          return res.status(401).json({ error: 'Invalid token for this chat.' });
        }

        // Delete the chat
        Chat.deleteOne({ id: chatId })
          .then(() => {
            return res.status(204).end();
          })
          .catch(error => {
            return res.status(500).json({ error: 'Failed to delete chat. Error: ' + error.message });
          });
      })
      .catch(error => {
        return res.status(500).json({ error: 'Failed to delete chat. Error: ' + error.message });
      });
  } catch (err) {
    // If the token is not valid, return an unauthorized error
    return res.status(401).json({ error: 'Invalid token.' });
  }
};
