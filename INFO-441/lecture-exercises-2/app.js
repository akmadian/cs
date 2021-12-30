const express = require('express')
const fs = require('fs')
const app = express()

app.all('/', function (req, res) {
  const newpath = './wwwroot' + (req.url === "/" ? "/index.html" : req.url);

  switch(req.url.split(".")[-1]) {
    case "html":
      res.set('Content-Type', 'text/html')
    case 'css':
      res.set('Content-Type', 'text/css')
    case 'txt':
      res.set('Content-Type', 'text/plain')
    default:
      break;
  }

  res.sendFile(newpath, { root: __dirname })
})

app.all('/api/getTime', function(req, res) {
  const d = new Date()
  res.send( {"date": d.toLocaleTimeString("en-US")} )
})

app.listen(3000, () => {
  console.log('Example app listening at http://localhost:3000')
})
