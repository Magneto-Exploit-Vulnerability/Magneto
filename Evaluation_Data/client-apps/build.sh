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
  "wakatime-sync"
  "WxJava/weixin-java-mp"
  "webbit"
  "weblaf/modules/core"
  "ZingClient"
  "wechat-ssm"
  "base-starter"
)

current_dir="$PWD"

function work() {
    (cd "$1" && mvn clean package -DskipTests)
}

for dir in "${client_projects_path[@]}"; do
  project_dir="$current_dir/$dir"
  work "$project_dir" &
done

wait

