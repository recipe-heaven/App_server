# Project no.twct.recipeheaven

**Requirements**

<p align="center">
  <a href="https://circleci.com/gh/vuejs/vue/tree/dev"><img src="https://img.shields.io/circleci/project/github/vuejs/vue/dev.svg?sanitize=true" alt="Build Status"></a>
  <a href="https://codecov.io/github/vuejs/vue?branch=dev"><img src="https://img.shields.io/codecov/c/github/vuejs/vue/dev.svg?sanitize=true" alt="Coverage Status"></a>
  <a href="https://npmcharts.com/compare/vue?minimal=true"><img src="https://img.shields.io/npm/dm/vue.svg?sanitize=true" alt="Downloads"></a>
  <a href="https://www.npmjs.com/package/vue"><img src="https://img.shields.io/npm/v/vue.svg?sanitize=true" alt="Version"></a>
  <a href="https://www.npmjs.com/package/vue"><img src="https://img.shields.io/npm/l/vue.svg?sanitize=true" alt="License"></a>
  <a href="https://chat.vuejs.org/"><img src="https://img.shields.io/badge/chat-on%20discord-7289da.svg?sanitize=true" alt="Chat"></a>
  <br>
  <a href="https://app.saucelabs.com/builds/50f8372d79f743a3b25fb6ca4851ca4c"><img src="https://app.saucelabs.com/buildstatus/vuejs" alt="Build Status"></a>
</p>

[![start with why](https://img.shields.io/badge/start%20with-why%3F-brightgreen.svg?style=flat)](http://www.ted.com/talks/simon_sinek_how_great_leaders_inspire_action)
[![Known Vulnerabilities](https://snyk.io/test/github/dwyl/hapi-auth-jwt2/badge.svg?targetFile=package.json)](https://snyk.io/test/github/dwyl/hapi-auth-jwt2?targetFile=package.json)
[![Build Status](https://travis-ci.org/dwyl/esta.svg?branch=master)](https://travis-ci.org/dwyl/esta)
[![codecov.io Code Coverage](https://img.shields.io/codecov/c/github/dwyl/hapi-auth-jwt2.svg?maxAge=2592000)](https://codecov.io/github/dwyl/hapi-auth-jwt2?branch=master)
[![Code Climate](https://codeclimate.com/github/dwyl/esta/badges/gpa.svg)](https://codeclimate.com/github/dwyl/esta)
[![JavaScript Style Guide: Good Parts](https://img.shields.io/badge/code%20style-goodparts-brightgreen.svg?style=flat)](https://github.com/dwyl/goodparts "JavaScript The Good Parts") 
[![NPM Version](https://badge.fury.io/js/esta.svg?style=flat)](https://npmjs.org/package/esta)
[![Join the chat at https://gitter.im/dwyl/chat](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/dwyl/chat?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) 
[![HitCount](http://hits.dwyl.com/dwyl/repo-badges.svg)](http://hits.dwyl.com/dwyl/repo-badges)

[![NPM Download Stats](https://nodei.co/npm/decache.png?downloads=true)](https://www.npmjs.com/package/decache)
- mvn
- Docker
- JDK 11

**Steps to run this project:**

1. Start your Docker daemon
2. Execute `./Build.sh` (Linux/MacOs)
3. This will launch
   1. Mail service
   2. Database (Postgres SQL)
   3. Application server

**Development**

1. Run ``` ./Build.sh``` - docker-compose up if images are already built
   1. Start ```./run.sh``` - This will destroy any application servers and create a separate application server mapping its volume to /target
2. Run `mvn package` in the root folder of the project for hot reloading the application server.
3. It will automatically restart the application
