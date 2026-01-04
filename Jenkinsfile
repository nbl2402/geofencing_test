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
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'

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
