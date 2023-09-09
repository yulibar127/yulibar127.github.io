const jwt = require('jsonwebtoken');
const Chat = require('../models/Chat');
const User = require('../models/User');

exports.addMessageToChat = (req, res) => {
  const bearerHeader = req.get('Authorization');
  if (!bearerHeader) {
    return res.status(401).json({ error: 'No token provided.' });
  }

  const token = bearerHeader.split(' ')[1];
  const chatId = req.params.id;
  const { msg } = req.body;

  try {
    // Verify the JWT token and decode it
    const decoded = jwt.verify(token, 'your-secret-key');

    Chat.findOne({ id: chatId })
      .populate('messages.sender', 'username displayName profilePic') // Populate the 'sender' field with specific fields
      .then(chat => {
        if (!chat) {
          return res.status(404).json({ error: 'Chat not found.' });
        }

        // Find the user based on the token's username
        User.findOne({ username: decoded.username })
          .then(sender => {
            if (!sender) {
              return res.status(404).json({ error: 'Sender not found.' });
            }

            // Create a new message object
            const newMessage = {
              id: chat.messages.length + 1, // Generate a new message ID based on the existing messages
              created: new Date(),
              sender: sender._id, // Assign the ObjectId of the sender
              content: msg // Set the content of the new message
            };

            // Add the new message to the chat's messages array
            chat.messages.push(newMessage);

            // Save the updated chat
            chat.save()
              .then(() => {
                // Find the newly added message
                const addedMessage = chat.messages[chat.messages.length - 1];

                // Create the response object with desired fields
                const response = {
                  id: addedMessage.id,
                  created: addedMessage.created,
                  sender: addedMessage.sender,
                  content: addedMessage.content
                };

                return res.status(200).json(response);
              })
              .catch(error => {
                return res.status(500).json({ error: 'Failed to add message to chat. Error: ' + error.message });
              });
          })
          .catch(error => {
            return res.status(500).json({ error: 'Failed to add message to chat. Error: ' + error.message });
          });
      })
      .catch(error => {
        return res.status(500).json({ error: 'Failed to add message to chat. Error: ' + error.message });
      });
  } catch (err) {
    // If the token is not valid, return an unauthorized error
    return res.status(401).json({ error: 'Invalid token.' });
  }
};
