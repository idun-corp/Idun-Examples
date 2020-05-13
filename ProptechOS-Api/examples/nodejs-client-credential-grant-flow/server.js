/*
*  Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
*  See LICENSE in the source repository root for complete license information.
*/

var express = require('express');
var morgan = require('morgan');
var path = require('path');
var querystring = require('querystring');
var request = require('request');

//initialize express.
var app = express();

// Initialize variables.
var port = 3000; // process.env.PORT || 3000;

// Configure morgan module to log all requests.
app.use(morgan('dev'));


// Set the front-end folder to serve public assets.
app.use(express.static('js'));

// Set up a route for index.html.
app.get('/', function (req, res) {
    res.sendFile(path.join(__dirname + '/index.html'));
});

app.get('/login', function (req, res) {

    var postData = {
        "client_id" : "<CLIENT_ID>",
        "client_secret" : "<CLIENT_SECRET>",
        "scope" : "<SCOPE_NAME>",
        "grant_type" : "client_credentials"
    };

    var post_data = querystring.stringify(postData);

    request({
        url: "<AUTHORITY_HREF>",
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
        },
        body: post_data
    }, function (error, response, body){
        res.writeHead(200, { 'Content-Type': 'application/json' });
        res.end(body, 'utf-8');
    });
});

// Start the server.
app.listen(port);
console.log('Listening on port ' + port + '...');
