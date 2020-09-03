const express = require('express');
const morgan = require('morgan');
const helmet = require('helmet');
const cors = require('cors');
const bodyParser = require('body-parser');
const path = require('path');
require('dotenv').config();

const app = express();

const port = process.env.PORT || 1337;

app.use(morgan('common'));
app.use(helmet());
app.use(cors({
  origin: process.env.CORS_ORIGIN
}));

app.use(express.static(path.join(__dirname,'../../dist/angular-example')));
app.use(bodyParser.json({limit: '50mb'}));
app.use(bodyParser.urlencoded({limit: '50mb', extended: true}));
app.use(express.json());

app.get('/*', (req, res) => {
  res.sendFile(path.join(__dirname,'../../dist/angular-example/index.html'));
});

app.post('/upload', upload);

app.listen(port, () => {
  console.log(`Listening at http://localhost:${port}`);
  console.log('NODE_ENV', process.env.NODE_ENV);
});


