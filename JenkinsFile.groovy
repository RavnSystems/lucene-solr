pipeline {
    agent any
    environment {
        PROJECT = 'solrMainJob'
    }
    tools {
        jdk 'OpenJDK 8'
    }
    stages {
        stage("ivy bootstrap") {
            steps {
                withAnt(installation: 'ant_1.10') {
                    sh "echo 'Bootstrapping'"
                    sh 'ant ivy-bootstrap'
                }
            }
        }
        stage("Build solr") {
            steps {
                withAnt(installation: 'ant_1.10') {
                    sh "echo 'building solr'"
                    sh "ant clean"
                    sh "ant create-package -f solr/build.xml"
                }
            }
        }
        stage("Archiving packages") {
            steps {
                archive 'solr/package/**/*.zip'
                archive 'solr/package/**/*.tgz'
            }
        }
    }
}