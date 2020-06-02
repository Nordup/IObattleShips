COMP = javac
JAVA = Main.java Map.java Interface.java Input.java BattleShip.java MyFile.java Time.java WinDrafts.java Battle.java
JAVA_CLASS = $(JAVA:%.java=%.class)
SRC = ./src/
OUT = ./out/
PACKAGE = IObattleShips/
JAVA_SRC = $(addprefix $(addprefix $(SRC), $(PACKAGE)), $(JAVA))
JAVA_OUT = $(addprefix $(addprefix $(OUT), $(PACKAGE)), $(JAVA_CLASS))

all: $(JAVA_OUT)

$(JAVA_OUT): $(JAVA_SRC)
		$(COMP) -classpath $(SRC) -d $(OUT) $(JAVA_SRC)

clean:
	rm -f $(JAVA_OUT)

fclean: clean

re: fclean all

.PHONY: all clean fclean re
