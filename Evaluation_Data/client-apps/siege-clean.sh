#! /bin/bash

client_projects_path=("adu-test"
  "adyen-api"
  "archivefs"
  "axon-server-se/axonserver"
  "bean-query"
  "CarStoreApi/account/account-web"
  "commons-validator"
  "db/engine"
  "elasticsearch-maven-plugin"
  "flow"
  "geek-framework"
  "gerenciador-viagens"
  "huntfiles"
  "idworker"
  "jerry-core"
  "jfinal"
  "JsonConfiguration"
  "kafka-keyvalue"
  "karate/karate-core"
  "knetbuilder/ondex-base/core/marshal"
  "mirage/mirage-core"
  "Mixmicro-Components/llc-kits"
  "neo"
  "ninja/ninja-core"
  "OmegaTester"
  "Online_Train_Ticket_Reservation_System/Code"
  "PatentPublicData/Common"
  "pdf-converter"
  "pdf-util"
  "PLMCodeTemplate/source"
  "QLExpress"
  "reproducible-build-maven-plugin"
  "rike/arago-rike-commons"
  "roubsite/RoubSiteUtils"
  "rpki-commons"
  "RuoYi-Vue-Multi-Tenant/multi-tenant-server"
  "serritor"
  "son-editor/son-validate-web"
  "tcpser4j"
  "twirl"
  "ucloud-java-sdk"
  "UltraPlaytime"
  "WxJava/weixin-java-mp"
  "wakatime-sync"
  "webbit"
  "weblaf/modules/core"
  "base-starter"
  "wechat-ssm"
  "ZingClient"
)

current_dir="$PWD"


for dir in "${client_projects_path[@]}"; do
  project_dir="$current_dir/$dir"
  cd "$project_dir" || exit
  rm -rf result.txt
done
