FROM node:10

ENV WORKSPACE=/opt/examples

WORKDIR /opt/examples

COPY ./key /tmp/


RUN \
    mkdir -p ~/.ssh \
    && echo 'Host GitHub.com\n\tStrictHostKeyChecking no\n' > ~/.ssh/config \
    && mv /tmp/key ~/.ssh/id_rsa \
    && chmod -R 0600 ~/.ssh \
    && eval "$(ssh-agent -s)" \
    && ssh-add -k ~/.ssh/id_rsa \
    && ssh-keyscan github.com >> $HOME/.ssh/known_hosts \
    && mkdir -p ${WORKSPACE} \
    && git clone -b master git@github.com:idun-corp/Idun-Examples.git  ${WORKSPACE} \
    && cd ${WORKSPACE}/ProptechOS-Api/examples/angular-actuation-application \
    && cd api \
    && npm install \
    && cd .. \
    && npm install \
    && npm run build:branchname \
    && rm -rf \
            /root/.git* \
            ~/.ssh \
            /tmp/*


CMD npm run start:branchname
