#!/bin/bash
#just run the database
cd distribution/target
tar -xvf blobcity-db-1.7.10-alpha.tar.gz
cd blobcity-db-1.7.10-alpha/bin
./blobcity.sh
