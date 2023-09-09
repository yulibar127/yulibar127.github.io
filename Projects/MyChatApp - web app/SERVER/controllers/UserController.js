const User = require('../models/User');
const UserPass = require('../models/UserPass');
const UserPassName = require('../models/UserPassName');
const bcrypt = require('bcrypt');

exports.register = (req, res) => {
  const { username, password, displayName, profilePic } = req.body;

  User.findOne({ username })
    .then(existingUser => {
      if (existingUser) {
        return res.status(409).json({ error: 'Username already exists.' });
      }

      bcrypt.hash(password, 10, (err, hashedPassword) => {
        if (err) {
          return res.status(500).json({ error: 'Failed to create user.' });
        }

        const newUserPassName = new UserPassName({
          username,
          password: hashedPassword,
          displayName,
          profilePic
        });

        const newUser = new User({
          username,
          displayName,
          profilePic
        });

        const newUserPass = new UserPass({
          username,
          password: hashedPassword
        });

        newUser.save()
          .then(() => {
            return newUserPass.save()
              .then(() => {
                return newUserPassName.save()
                  .then((userpn) => {
                    return res.json(userpn);
                  })
                  .catch((error) => {
                    return res.status(500).json({ error: 'Failed to create userpn.' });
                  });
              })
              .catch((error) => {
                return res.status(500).json({ error: 'Failed to create userp.' });
              });
          })
          .catch((error) => {
            return res.status(500).json({ error: 'Failed to create user.' });
          });
      });
    })
    .catch((error) => {
      return res.status(500).json({ error: 'Failed to create user.' });
    });
};
