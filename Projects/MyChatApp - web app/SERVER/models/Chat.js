const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
  username: {
    type: String,
    required: true
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

const messageSchema = new mongoose.Schema({
  id: {
    type: Number,
    required: true
  },
  created: {
    type: Date,
    required: true,
    default: Date.now
  },
  sender: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'User',
    required: true
  },
  content: {
    type: String,
    required: true
  }
});

const chatSchema = new mongoose.Schema({
  id: {
    type: Number,
    required: true
  },
  users: {
    type: [userSchema],
    default: undefined
  },
  messages: {
    type: [messageSchema],
    default: undefined
  }
});

const Chat = mongoose.model('Chat', chatSchema);

module.exports = Chat;
