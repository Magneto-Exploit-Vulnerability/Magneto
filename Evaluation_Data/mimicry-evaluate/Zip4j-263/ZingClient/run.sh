#!/bin/bash
start_time=$(date +%s)

ROOT_DIR=`dirname $0`
# prepare environment
if [ ! -d "$ROOT_DIR/evosuite-report" ]; then
  
  mkdir -p "$ROOT_DIR/evosuite-report"
fi

projectCP=`bash ./classpath.sh`

java -jar ../../evosuite-master-1.1.1-SNAPSHOT.jar \
-class zing.client.file.ZFile \
-projectCP $projectCP \
-Djunit=com.magneto.Zip4j_263_Testcase1 \
-Dselected_junit=com.magneto.Zip4j_263_Testcase1 \
-Dcarve_object_pool=true \
-Dsearch_budget=600

end_time=$(date +%s)
execution_time=$((end_time - start_time))
echo "Script executed in $execution_time seconds." > ./time_log.txt