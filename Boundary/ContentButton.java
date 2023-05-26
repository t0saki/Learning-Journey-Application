package Boundary;

import Control.GlobalColors;

public class ContentButton extends MyButton{
    public ContentButton(String buttonName) {
        super(buttonName);
        setColors(GlobalColors.lighterBlue, GlobalColors.darkerBlue, GlobalColors.lighterBlue,
                GlobalColors.lighterBlue, GlobalColors.solidWhite);
    }
}
