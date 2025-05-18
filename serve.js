#!/usr/bin/env node

const http = require('http');
const fs = require('fs');
const path = require('path');
const url = require('url');

// Configuration
const PORT = 8080;
const MIME_TYPES = {
  '.html': 'text/html',
  '.js': 'text/javascript',
  '.css': 'text/css',
  '.json': 'application/json',
  '.wasm': 'application/wasm',
  '.png': 'image/png',
  '.jpg': 'image/jpeg',
  '.gif': 'image/gif',
  '.svg': 'image/svg+xml',
  '.ico': 'image/x-icon',
  '.txt': 'text/plain',
};

// Create the server
const server = http.createServer((req, res) => {
  // Parse URL
  const parsedUrl = url.parse(req.url);
  
  // Extract the path
  let pathname = `.${parsedUrl.pathname}`;
  
  // Maps the "/" path to "./index.html"
  if (pathname === './') {
    pathname = './index.html';
  }
  
  // Resolve the file path
  const filepath = path.resolve(pathname);
  
  // Get the file extension
  const ext = path.extname(filepath);
  
  // Determine the content type
  const contentType = MIME_TYPES[ext] || 'application/octet-stream';
  
  // Read file
  fs.readFile(filepath, (err, data) => {
    if (err) {
      if (err.code === 'ENOENT') {
        // File not found
        res.writeHead(404);
        res.end(`File ${pathname} not found!`);
        return;
      }
      
      // Some server error
      res.writeHead(500);
      res.end(`Error getting the file: ${err.code}`);
      return;
    }
    
    // File found, send it
    res.writeHead(200, { 'Content-Type': contentType });
    res.end(data);
  });
});

// Start the server
server.listen(PORT, () => {
  console.log(`Pascal-to-WebAssembly server running at http://localhost:${PORT}/`);
  console.log(`Press Ctrl+C to stop the server`);
}); 