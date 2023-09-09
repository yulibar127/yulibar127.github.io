const mongoose = require('mongoose');

const userPassNameSchema = new mongoose.Schema({
  username: {
    type: String,
    unique: true,
    sparse: true,
    nullable: true
  },
  password: {
    type: String,
    nullable: true
  },
  displayName: {
    type: String,
    nullable: true
  },
  profilePic: {
    type: String,
    nullable: true
  }
});

const UserPassName = mongoose.model('UserPassName', userPassNameSchema);

module.exports = UserPassName;
