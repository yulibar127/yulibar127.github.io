const jwt = require('jsonwebtoken');
const Chat = require('../models/Chat');

exports.getMessageFromChat = (req, res) => {
  const bearerHeader = req.get('Authorization');
  if (!bearerHeader) {
    return res.status(401).json({ error: 'No token provided.' });
  }

  const token = bearerHeader.split(' ')[1]; // Get token from 'Bearer token'
  const chatId = req.params.id; // Get chat ID from URL parameter

  try {
    // Verify the JWT token
    jwt.verify(token, 'your-secret-key');

    // Find the chat based on the chat ID
    Chat.findOne({ id: parseInt(chatId) }) // Convert chatId to Number
      .populate('messages.sender', 'username displayName profilePic')
      .then(chat => {
        if (!chat) {
          return res.status(404).json({ error: 'Chat not found.' });
        }
        const reversedMessages = chat.messages.reverse(); // Reverse the order of messages
        return res.status(200).json(reversedMessages);
      })
      .catch(error => {
        console.error('Error retrieving chat:', error);
        return res.status(500).json({ error: 'Failed to get messages from chat. Error: ' + error.message });
      });
  } catch (err) {
    console.error('Invalid token:', err);
    return res.status(401).json({ error: 'Invalid token.' });
  }
};
