#!/bin/bash
IP="192."

base=`dirname $0` 
cd $base

STARTER=`ls ../start.jar`
IP=`/sbin/ifconfig |grep "$IP"|awk -F'addr:' '{print $2}'|awk '{print $1}'`
IP=`echo $IP|awk "{print $1}"`
FLAGS=" -Dappname=${artifactId} -Dorg.terracotta.quartz.skipUpdateCheck=true "

cmd="java "$FLAGS" -XX:MaxPermSize=128m -Xms256m -Xmx1024m -jar $STARTER --ini=../start.ini"
$cmd
