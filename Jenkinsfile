#!groovy
pipeline {
    agent {
        docker {
            image 'androidsdk/android-28'
            args '-it --memory=12g --cpus="4"'
        }
    }
    stages {
        stage('clone') {
            steps {
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],
                        extensions: [],
                        userRemoteConfigs: [[url: 'https://github.com/vyachesval/rssreader', credentialsId: 'e80592eb-803e-4a83-9542-db74af422419']]
                ])
            }
        }
        stage("init") {
            steps {
                sh "chmod +x gradlew"
                sh "./gradlew"
            }
        }
        stage("lint") {
            steps {
                sh "./gradlew lintDebug"
            }
        }
        stage("test") {
            steps {
                sh "./gradlew testDebugUnitTest"
            }
        }
        stage("build") {
            steps {
                sh "./gradlew assembleDebug"
            }
        }
    }
    post {
        always {
            archiveArtifacts(artifacts: '**/build/reports/**', allowEmptyArchive: true)
        }
    }
}