package com.revature.music.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StringValidationService {

  public Boolean checkLength(String check, Integer min, Integer max)
  {
    if (check.length() > max && check.length()<min)
    {
      return false;
    }
    return true;
  }
  public Boolean checkLengthMax(String check, Integer max)
  {
    if (check.length() > max)
    {
      return false;
    }
    return true;
  }
  public Boolean isBlank(String check)
  {
    return check.isEmpty();
  }

  public Boolean checkLengthMin(String check, Integer min)
  {
    if ( check.length()<min)
    {
      return false;
    }
    return true;
  }
}
