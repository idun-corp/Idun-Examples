FROM node:10

ENV APP_DIR=/opt/actuationui
ENV WORKSPACE=/opt/examples

WORKDIR /opt/actuationui


RUN \
    mkdir -p ${APP_DIR} \
    && git clone -b master git@github.com:idun-corp/Idun-Examples.git  ${WORKSPACE} \
    && cp -r ${WORKSPACE}/ProptechOS-Api/examples/angular-actuation-application ${APP_DIR}
    && cd ${APP_DIR} \
    && cd api \
    && npm install \
    && cd .. \
    && npm install \
    && npm run build:branchname \
    && rm -rf \
            /root/.git* \
            /opt/examples \
            /tmp/*


CMD npm run start:branchname
