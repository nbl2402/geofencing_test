pipeline {
    agent any

    environment {
        GIT_CREDENTIALS_ID = '5b1f3100-599b-456e-84d3-b92de8c86f59' // ID credential GitHub trong Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git(
                    url: 'https://github.com/nbl2402/geofencing_test.git',
                    credentialsId: "${GIT_CREDENTIALS_ID}",
                    branch: 'main'
                )
            }
        }

        stage('Run Tests') {
            steps {
                sh '''
                docker run --rm -v $WORKSPACE:/app -v $HOME/.m2:/root/.m2 -w /app maven:3.9.3-openjdk-17 mvn clean test
                ls -l target/surefire-reports
                '''
            }
        }
    }

    post {
        always {
            sh 'ls -l target/surefire-reports'
            junit 'target/surefire-reports/*.xml'
            echo 'Pipeline finished'
        }
        success {
            echo 'All tests passed!'
        }
        failure {
            echo 'Some tests failed!'
        }
    }
}
