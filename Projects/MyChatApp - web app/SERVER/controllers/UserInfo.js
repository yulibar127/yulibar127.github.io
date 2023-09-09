const User = require('../models/User');
const UserPass = require('../models/UserPass');
const UserPassName = require('../models/UserPassName');
const jwt = require('jsonwebtoken');

exports.getUserInfo = (req, res) => {
  const username = req.params.username; // Get username from URL parameter
  const bearerHeader = req.get('Authorization'); // Get bearer token from 'Authorization' header

  if (!bearerHeader) {
    return res.status(401).json({ error: 'No token provided.' });
  }

  const token = bearerHeader.split(' ')[1]; // Get token from 'Bearer token'

  try {
    // Verify and decode the JWT token
    const decoded = jwt.verify(token, 'your-secret-key');

    // Check if the username from the request matches the username from the token
    if (decoded.username !== username) {
      return res.status(401).json({ error: 'Invalid token or mismatched username.' });
    }

    // Get user's information from the database
    User.findOne({ username })
      .then(user => {
        if (!user) {
          return res.status(404).json({ error: 'User not found.' });
        }

        UserPass.findOne({ username })
          .then(userPass => {
            if (!userPass) {
              return res.status(404).json({ error: 'User password not found.' });
            }

            UserPassName.findOne({ username })
              .then(userPassName => {
                if (!userPassName) {
                  return res.status(404).json({ error: 'User display name not found.' });
                }

                // Return the user's information
                const userInfo = {
                  username: user.username,
                  profilePic : user.profilePic,
                  displayName: userPassName.displayName
                };

                return res.status(200).json(userInfo);
              })
              .catch((error) => {
                return res.status(500).json({ error: 'Failed to get user information.' });
              });
          })
          .catch((error) => {
            return res.status(500).json({ error: 'Failed to get user information.' });
          });
      })
      .catch((error) => {
        return res.status(500).json({ error: 'Failed to get user information.' });
      });
  } catch (err) {
    // If the token is not valid, return an unauthorized error
    return res.status(401).json({ error: 'Invalid token.' });
  }
};