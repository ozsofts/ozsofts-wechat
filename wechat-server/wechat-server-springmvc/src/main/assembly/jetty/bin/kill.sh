#!/bin/bash
ps aux | grep java |grep "${artifactId}" | awk '{print $2}' | xargs kill
rm -rf /duitang/logs/usr/japa/performance.log
