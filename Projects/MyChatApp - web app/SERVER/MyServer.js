// server.js
const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const cors = require('cors');
const apiRoutes = require('./routes/apiRoutes');
const WebSocket = require('ws');

const app = express();
const port = 5000;

app.use(cors());


// Parse incoming request bodies
app.use(bodyParser.json({ limit: '10mb' }));

// Connect to MongoDB

mongoose.connect('mongodb+srv://server_user:server_user123@cluster0.wn2ge7t.mongodb.net/?retryWrites=true&w=majority', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
})
  .then(() => {

  })
  .catch((error) => {
    console.error('Failed to connect to MongoDB', error);
  });

// Use the API routes
app.use('/api', apiRoutes);


// Start the server
const server = app.listen(port, () => {
});

const wss = new WebSocket.Server({ server });


// Handle WebSocket connection
wss.on('connection', (ws) => {

  // Handle incoming messages
  ws.on('message', (message) => {
    // Broadcast the message to all connected clients
    wss.clients.forEach((client) => {
      if (client !== ws && client.readyState === WebSocket.OPEN) {
        client.send(message);
      }
    });
  });
});