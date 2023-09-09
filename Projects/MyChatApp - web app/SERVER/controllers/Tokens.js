const User = require('../models/User'); 
const UserPass = require('../models/UserPass');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
exports.login = (req, res) => {
  const { username, password } = req.body;

  // Find the user
  User.findOne({ username })
    .then(user => {
      if (!user) {
        return res.status(401).json({ error: 'Invalid username or password.' });
      }

      // Check password
      UserPass.findOne({ username })
        .then(userPass => {
          if (!userPass) {
            return res.status(401).json({ error: 'Invalid username or password.' });
          }

          bcrypt.compare(password, userPass.password, (err, result) => {
            if (err) {
              return res.status(500).json({ error: 'Failed to authenticate.' });
            }

            if (result) {
              // Password is correct, generate and return a token
              const token = jwt.sign({ username: user.username }, 'your-secret-key', { expiresIn: '10h' });

              // Return just the token
              return res.status(200).send(token);
            } else {
              // Password is incorrect
              return res.status(401).json({ error: 'Invalid username or password.' });
            }
          });
        })
        .catch((error) => {
          return res.status(500).json({ error: error.message });
        });
    })
    .catch((error) => {
      return res.status(500).json({ error: error.message });
    });
};