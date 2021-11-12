const withTM = require("next-transpile-modules")(["common"]);

/** @type {import('next').NextConfig} */
module.exports = withTM({
  reactStrictMode: true,
});
