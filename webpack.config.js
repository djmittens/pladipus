module.exports = {
  entry: './src/frontend/javascript/main.js',
  output: {
    filename: 'build/frontend/bundle.js'
  },
  module: {
    loaders: [{
      test: /\.js$/,
      loader: 'babel-loader',
      query: {
        presets: ['es2015', 'react']
      }
    }]
  },

  resolve: {
    extensions: ['', '.js', '.json']
  }
};
