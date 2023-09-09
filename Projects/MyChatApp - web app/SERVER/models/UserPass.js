const mongoose = require('mongoose');

const userPassSchema = new mongoose.Schema({
  username: {
    type: String,
    unique: true,
    sparse: true,
    nullable: true
  },
  password: {
    type: String,
    nullable: true
  }
});

const UserPass = mongoose.model('UserPass', userPassSchema);

module.exports = UserPass;
