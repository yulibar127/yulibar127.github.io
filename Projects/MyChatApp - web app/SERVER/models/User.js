const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
  username: {
    type: String,
    unique: true,
    sparse: true,
    required: false
  },
  displayName: {
    type: String,
    required: false
  },
  profilePic: {
    type: String,
    required: false
  }
});


const User = mongoose.model('User', userSchema);

module.exports = User;
