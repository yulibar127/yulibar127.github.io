const express = require('express');
const router = express.Router();
const UserController = require('../controllers/UserController');
const Tokens = require('../controllers/Tokens');
const getUserInfo = require( '../controllers/UserInfo');
const ChatController = require('../controllers/CreateChat');
const ChatUser = require('../controllers/getUserChats');
const ChatIdGet = require('../controllers/getChatById');
const ChatIdDel = require('../controllers/deleteChatById');
const addmessge = require('../controllers/addMessage');
const getmsg = require('../controllers/getMessageFromChat');

router.post('/Users', UserController.register);
router.post('/Tokens', Tokens.login);
router.get('/Users/:username', getUserInfo.getUserInfo);
router.post('/Chats', ChatController.createChat);
router.get('/Chats', ChatUser.getUserChats); 
router.get('/Chats/:id', ChatIdGet.getChatById); 
router.delete('/Chats/:id', ChatIdDel.deleteChatById);
router.post('/Chats/:id/Messages', addmessge.addMessageToChat);
router.get('/Chats/:id/Messages', getmsg.getMessageFromChat);


module.exports = router;
