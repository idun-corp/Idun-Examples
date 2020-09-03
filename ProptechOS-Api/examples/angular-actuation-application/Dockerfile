FROM node:10

ENV APP_DIR=/opt/actuationui
ENV WORKSPACE=/opt/examples

WORKDIR /opt/actuationui

COPY ./key /tmp/


RUN \
    mkdir -p ~/.ssh \
    && echo 'Host GitHub.com\n\tStrictHostKeyChecking no\n' > ~/.ssh/config \
    && mv /tmp/key ~/.ssh/id_rsa \
    && chmod -R 0600 ~/.ssh \
    && eval "$(ssh-agent -s)" \
    && ssh-add -k ~/.ssh/id_rsa \
    && ssh-keyscan github.com >> $HOME/.ssh/known_hosts \
    && mkdir -p ${APP_DIR} \
    && git clone -b master git@github.com:idun-corp/Idun-Examples.git  ${WORKSPACE} \
    && cp -r ${WORKSPACE}/ProptechOS-Api/examples/angular-actuation-application ${APP_DIR} \
    && cd ${APP_DIR} \
    && cd api \
    && npm install \
    && cd .. \
    && npm install \
    && npm run build:branchname \
    && rm -rf \
            /root/.git* \
            /opt/examples \
            ~/.ssh \
            /tmp/*


CMD npm run start:branchname
