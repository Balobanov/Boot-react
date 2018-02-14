var path = require('path');
var webpack = require('webpack');
const cssNext = require('postcss-cssnext');
const ExtractTextPlugin = require("extract-text-webpack-plugin");

const extractSass = new ExtractTextPlugin({
  filename: "[name].[contenthash].css"
  // disable: process.env.NODE_ENV === "development"
});

module.exports = {
  entry: './src/main.js',
  output: {
    path: path.resolve(__dirname, './dist'),
    publicPath: '/dist/',
    filename: 'build.js'
  },
  plugins: [
    extractSass
  ],
  module: {
    rules: [
      {
        test: /\.scss$/,
        use: [{
          loader: 'style-loader',
        },
          {
            loader: 'css-loader',
            options: {
              sourceMap: true,
              minimize: false,
            },
          },
          {
            loader: 'postcss-loader',
            options: {
              sourceMap: true,
              plugins: () => [
                cssNext(),
              ],
            },
          },
          {
            loader: 'sass-loader',
            options: {
              sourceMap: true,
              includePaths: [
                path.join('./', 'node_modules'),
                path.join('./src', 'assets', 'styles'),
                path.join('./dist', ''),
              ],
            },
          }],
      },

      {
        test: /\.vue$/,
        loader: 'vue-loader',
        options: {
          loaders: {}
          // other vue-loader options go here
        }
      },
      {
        test: /\.js$/,
        loader: 'babel-loader',
        exclude: /node_modules/
      },
      {
        test: /\.(eot|svg|ttf|woff|woff2)$/,
        exclude: /(node_modules)/,
        use: ['file-loader'],
      },
      {
        test: /\.(png|gif|jpg|svg)$/i,
        exclude: /(node_modules)/,
        use: [{
          loader: 'file-loader',
          options: {
            outputPath: 'assets',
          },
        }],
      }
    ]
  },
  resolve: {
    alias: {
      'vue$': 'vue/dist/vue.esm.js'
    },
    extensions: ['*', '.js', '.vue', '.json']
  },
  devServer: {
    historyApiFallback: true,
    noInfo: true,
    overlay: true,
    port: 9000,
    proxy: {
      "/oauth/*": {
        target: "http://localhost:8080",
      },

      "/user/*": {
        target: "http://localhost:8080",
      },

      "/box/*": {
        target: "http://localhost:8080",
      },

      "/counter/*": {
        target: "http://localhost:8080",
      }
    }
  },
  performance: {
    hints: false
  },
  devtool: '#eval-source-map'
};

if (process.env.NODE_ENV === 'production') {
  module.exports.devtool = '#source-map';
  module.exports.plugins = (module.exports.plugins || []).concat([
    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: '"production"'
      }
    }),
    new webpack.optimize.UglifyJsPlugin({
      sourceMap: true,
      compress: {
        warnings: false
      }
    }),
    new webpack.LoaderOptionsPlugin({
      minimize: true
    })
  ])
}

// {
//   test: /\.css$/,
//   use: [
//     'vue-style-loader',
//     'css-loader'
//   ]
// }
