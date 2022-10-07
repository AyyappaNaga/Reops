cd gridsetup
start cmd /k call hub.bat
start cmd /k call node.bat
start cmd /k call node1.bat
timeout 10
cd..
start cmd /k call Runner.bat
