1. Start elastic search
    - token password will be generated for first time
    - username: kibana_system

2. Open \config\kibana.yml
    - change username - "kibana_system"
    - change username - enter generated password
        - if dont work (bin\elasticsearch-reset-password.bat --username kibana_system) from elastic path

3. Start bin\kibana.bat
    - go to link shown in cmd console
    - configure manually
    - username "kibana_system"
    - password : enter password
    - (This will upadate the username & password)

4. again kibana will ask for credentials
   - username -> "elastic"
   - password -> enter password (bin\elasticsearch-reset-password.bat --username elastic)