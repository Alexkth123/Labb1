pipeline {
    agent {
        label "node1"
    }
    tools {
        jdk 'Java17'
        maven 'Maven3'
    }
    stages {
        stage("Clean up workspace") {
            steps {
                cleanWs()
            }
        }
        stage("Check out SCM") {
            steps {
                checkout scm: [
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[
                        credentialsId: 'github',
                        url: 'https://github.com/Alexkth123/Labb1'
                    ]]
                ]
            }
        }
        stage("Build Application") {
            steps {
                sh "mvn clean package"
            }
        }

        stage("Test Application") {
            steps {
                sh "mvn test"
            }
        }

    }
}
