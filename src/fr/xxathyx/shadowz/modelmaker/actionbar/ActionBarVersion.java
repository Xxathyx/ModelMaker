package fr.xxathyx.shadowz.modelmaker.actionbar;

import org.bukkit.Bukkit;

import fr.xxathyx.shadowz.modelmaker.translation.Translater;
import fr.xxathyx.shadowz.modelmaker.util.ActionBar;

public class ActionBarVersion {

  public ActionBar getActionBar() {

    final String serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

    if (serverVersion.equals("v1_15_R1")) {
      return new v1_15_R1();
    }
    if (serverVersion.equals("v1_14_R1")) {
      return new v1_14_R1();
    }
    if (serverVersion.equals("v1_13_R2")) {
      return new v1_13_R2();
    }
    if (serverVersion.equals("v1_13_R1")) {
      return new v1_13_R1();
    }
    if (serverVersion.equals("v1_12_R1")) {
      return new v1_12_R1();
    }
    if (serverVersion.equals("v1_11_R1")) {
      return new v1_11_R1();
    }
    if (serverVersion.equals("v1_10_R1")) {
      return new v1_10_R1();
    }
    if (serverVersion.equals("v1_9_R2")) {
      return new v1_9_R2();
    }
    if (serverVersion.equals("v1_9_R1")) {
      return new v1_9_R1();
    }
    if (serverVersion.equals("v1_8_R3")) {
      return new v1_8_R3();
    }
    if (serverVersion.equals("v1_8_R2")) {
      return new v1_8_R2();
    }
    if (serverVersion.equals("v1_8_R1")) {
      return new v1_8_R1();
    }
    System.out.print(Translater.getServerLangage().getMessage(Translater.getServerLangage().version_non_supported()));
    return null;
  }
}