# fantasyDraftRandomizer
Sets a draft order for a fantasy league based on a few rules:

- In reverse order of prior finish, you can't select worse than 2 spots below where you "should" pick.
  For example, if someone finishes last in a league, the worst they would pick is third

- After the first pick is established, the probabilities are recalculated with the previously chosen player's probability removed.
  For example, if the probability of the person selecting first was 20%, the remaining probabilities are multiplied by 1/0.8. This resets the total probability for the second pick to 1.0
  This is repeated every time a player is "selected" to pick next
  
  
