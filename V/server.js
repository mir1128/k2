var fs = require('fs');
var path = require('path');
var express = require('express');
var bodyParser = require('body-parser');
var app = express();


app.set('port', (process.env.PORT || 3000));

app.use('/', express.static(path.join(__dirname, 'src')));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));


var names = [];

app.get('/k2/api/register/check/:name', function(req, res) {
  if (names.indexOf(req.params.name) == -1) {
    res.json({'result':true});
  } else {
    res.json({'result':false});
  }
});

app.post('/k2/api/register/:name', function(req, res) {
  ret = {};
  if (names.indexOf(req.params.name) == -1) {
    names.push(req.params.name);
    ret['name'] = req.params.name;
    ret['result'] = 'ok',
    ret['status'] = '0,0,0,0,0,0,2,0,4,0,0,0,0,0,0,0';
    res.json(ret);
  } else {
    ret['result'] = 'failed';
    ret['message'] = req.params.name + ' already used.';
    res.status(403).json(ret);
  }
});

app.post('/k2/api/move/:name/:direction', function(req, res) {
    ret = {};
    ret['name'] = req.params.name;
    ret['result'] = 'ok',
    ret['status'] = '2, 4, 8, 16, 0, 0, 0, 0, 2, 4, 8, 16, 0, 0, 0, 0';
    res.json(ret);
});

app.get('/k2/api/scores', function(req, res) {
  retStr = '[{"name":"ggg7","score":40},{"name":"ggg5","score":16},{"name":"ggg6","score":12},{"name":"aaa4","score":0},{"name":"ggg8","score":0},{"name":"ggg4","score":0},{"name":"aaa3","score":0},{"name":"ggg9","score":0},{"name":"aaa2","score":0},{"name":"aaa1","score":0}]';
  res.json(JSON.parse(retStr));
});


app.listen(app.get('port'), function() {
  console.log('Server started: http://localhost:' + app.get('port') + '/');
});
