# MSPRJava
        steps {
            echo "currentPath=$(pwd)"
            sh 'cd ..'
            echo "imagePath=$(pwd)"
            sh 'cp -R $currentPath/docker-nginx $imagePath/docker-nginx'
        }
