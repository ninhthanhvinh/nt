class Participant:
  def __init__(self):
    self.points = 0
    self.choice = ""
  def Choose(self):
    self.choice = input("{name}, select rock, paper or scissor:".format(name= self.name))
    print("{name} select {choice}".format(name=self.name, choice=self.choice))

#class GameRound:

class Game:
   def __init__(self):
     self.endGame = False
     self.participant = Participant()
     self.secondParticipant = Participant()
