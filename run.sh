#!/usr/bin/env bash

if [ -z "$1" ]
then
   echo "Please provide expression in quotes."
   exit 1;
fi

java -cp    target/compiler-1.0-SNAPSHOT.jar  com.hrishikeshmishra.compiler.Main "$1"