const webpack = require('webpack');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const UglifyJSPlugin = require('uglifyjs-webpack-plugin');

const VENDOR_LIBS = [
    'axios', 'react', 'lodash', 'redux', 'react-redux', 'react-dom',
    'faker', 'react-input-range', 'redux-form', 'redux-thunk'
];

module.exports = {
    entry: {
        bundle: './src/app.js',
        vendor: VENDOR_LIBS
    },
    output: {
        path: path.join(__dirname, './target/static'),
        filename: '[name].[hash].js'
    },
    module: {
        rules: [
            {
                use: 'babel-loader',
                test: /\.js$/,
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                use: ExtractTextPlugin.extract({
                    fallback: "style-loader",
                    use: "css-loader"
                })
            }
        ]
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            names: ['vendor', 'manifest']
        }),
        new ExtractTextPlugin("styles.css"),
        new HtmlWebpackPlugin({
            template: 'src/html/index.html'
        }),
        new UglifyJSPlugin({
            sourceMap: true
        }),
        new webpack.DefinePlugin({
            'process.env.NODE_ENV': JSON.stringify('production')
        })
    ]
};
