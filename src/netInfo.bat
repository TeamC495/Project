mode con: cols=90 lines=50
@echo *******************This is your Gateway IP Address*******************
@echo.
ipconfig | findstr /i "Gateway"
@echo.
@echo ******Find the Physical Address for your Gateway in this table******
@echo.
arp -a

pause