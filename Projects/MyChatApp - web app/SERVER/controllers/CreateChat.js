const jwt = require('jsonwebtoken');
const Chat = require('../models/Chat');
const User = require('../models/User');

exports.createChat = (req, res) => {
  const bearerHeader = req.get('Authorization');
  if (!bearerHeader) {
    return res.status(401).json({ error: 'No token provided.' });
  }

  const token = bearerHeader.split(' ')[1]; // Get token from 'Bearer token'

  try {
    // Verify the JWT token and decode it
    const decoded = jwt.verify(token, 'your-secret-key');

    // Get the username from the request body
    const { username } = req.body;

    // Find the sender user based on the token
    User.findOne({ username: decoded.username })
      .then(sender => {
        if (!sender) {
          return res.status(404).json({ error: 'Sender not found.' });
        }

        // Find the recipient user
        User.findOne({ username })
          .then(recipient => {
            if (!recipient) {
              return res.status(404).json({ error: 'Recipient not found.' });
            }

            Chat.countDocuments()
              .exec()
              .then(count => {
                const newChat = new Chat({
                  id: count + 1,
                  users: [
                    {
                      username: sender.username,
                      displayName: sender.displayName,
                      profilePic: sender.profilePic
                    },
                    {
                      username: recipient.username,
                      displayName: recipient.displayName,
                      profilePic: recipient.profilePic
                    }
                  ],
                  messages: []
                });

                newChat.save()
                  .then(chat => {
                    return res.json(chat);
                  })
                  .catch(error => {
                    return res.status(500).json({ error: 'Failed to create chat. Error: ' + error.message });
                  });
              })
              .catch(error => {
                return res.status(500).json({ error: 'Failed to create chat. Error: ' + error.message });
              });
          })
          .catch(error => {
            return res.status(500).json({ error: 'Failed to create chat. Error: ' + error.message });
          });
      })
      .catch(error => {
        return res.status(500).json({ error: 'Failed to create chat. Error: ' + error.message });
      });
  } catch (err) {
    // If the token is not valid, return an unauthorized error
    return res.status(401).json({ error: 'Invalid token.' });
  }
};
