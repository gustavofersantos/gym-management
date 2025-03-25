const webpack = require('webpack');

module.exports = function override(config) {
  const oneOfRule = config.module.rules.find(
    (rule) => rule.oneOf
  );

  if (oneOfRule) {
    const tsxRule = oneOfRule.oneOf.find(
      (rule) => rule.test && rule.test.toString().includes('tsx')
    );

    const cssLoader = oneOfRule.oneOf.find(
      (rule) => rule.test && rule.test.toString().includes('css')
    );

    if (tsxRule) {
      tsxRule.include = undefined;
      tsxRule.exclude = /node_modules/;
    }

    if (cssLoader) {
      cssLoader.use.push({
        loader: require.resolve('postcss-loader'),
        options: {
          postcssOptions: {
            ident: 'postcss',
            plugins: [
              require('tailwindcss'),
              require('autoprefixer'),
            ],
          },
        },
      });
    }
  }

  return config;
};