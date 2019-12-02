import React from "react";
import mapBg from "../../assets/images/map.png";
import { createStyles, Theme, makeStyles } from "@material-ui/core/styles";
import { relative } from "path";
import { School as SchoolIcon } from "@material-ui/icons";
import { IconButton } from "@material-ui/core";

const locations = [
  { left: "10%", top: "48%" },
  { left: "28%", top: "48%" },
  { left: "48%", top: "55%" },
  { left: "63%", top: "55%" },
  { left: "77%", top: "55%" },
  { left: "77%", top: "64%" },
  { left: "77%", top: "72%" },
  { left: "77%", top: "81%" },
  { left: "72%", top: "90%" },
  { left: "61%", top: "88%" },
  { left: "51%", top: "88%" },
  { left: "47%", top: "80%" },
  { left: "28%", top: "70%" },
  { left: "82%", top: "18%" },
  { left: "94%", top: "8%" },
  { left: "90%", top: "43%" },
  { left: "90%", top: "31%" },
  { left: "64%", top: "4%" },
  { left: "46%", top: "4%" }
];

const useStyles = makeStyles<Theme>((theme: Theme) =>
  createStyles({
    container: {
      position: "relative",
      left: "-5%",
      top: 0,
      height: "100%",
      width: "100%",
      paddingTop: "95%"
    },
    root: {
      padding: 36,
      minWidth: 420
    }
  })
);

// 0.78

const FloorMap: React.FC = () => {
  const classes = useStyles();
  return (
    <div className={classes.root}>
      <div className={classes.container}>
        <div
          style={{
            position: "absolute",
            left: "5%",
            top: "47%",
            width: "85%",
            height: "57%",
            backgroundColor: "lightgray"
          }}
        ></div>
        <div
          style={{
            position: "absolute",
            left: "40%",
            top: "0%",
            width: "65%",
            height: "57%",
            backgroundColor: "lightgray"
          }}
        ></div>
        {locations.map((value, index) => (
          <IconButton
            style={{ position: "absolute", left: value.left, top: value.top }}
          >
            <SchoolIcon />
          </IconButton>
        ))}
      </div>
    </div>
  );
};

export default FloorMap;
