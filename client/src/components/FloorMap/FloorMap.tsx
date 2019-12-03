import React from "react";
import { createStyles, Theme, makeStyles } from "@material-ui/core/styles";
import { School as SchoolIcon } from "@material-ui/icons";
import { IconButton, Box, Badge } from "@material-ui/core";
import { Class } from "../../types";

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

const rooms = [
  { left: "5%", top: "45%", width: "87%", height: "60%" },
  { left: "43%", top: "0%", width: "65%", height: "57%" }
];

const useStyles = makeStyles<Theme>((theme: Theme) =>
  createStyles({
    innerContainer: {
      position: "relative",
      left: "-5%",
      top: 0,
      height: "100%",
      width: "100%",
      paddingTop: "95%"
    },
    outerContainer: {
      minWidth: 480,
      maxWidth: 720,
      width: "100%"
    },
    root: {
      display: "flex",
      justifyContent: "center",
      width: "100%",
      padding: 36
    }
  })
);

interface FloorMapProps {
  classes: Class[];
  setClassId: (id: number) => void;
}

const FloorMap: React.FC<FloorMapProps> = props => {
  const classes = useStyles();
  return (
    <div className={classes.root}>
      <div className={classes.outerContainer}>
        <div className={classes.innerContainer}>
          {rooms.map(({ left, top, width, height }, index) => {
            return (
              <Box
                position="absolute"
                left={left}
                top={top}
                width={width}
                height={height}
                bgcolor="lightgray"
              ></Box>
            );
          })}
          {props.classes.map((value, index) => {
            const { left, top } = locations[value.loc_id];
            return (
              <Box position="absolute" left={left} top={top}>
                <IconButton onClick={() => props.setClassId(value.class_id)}>
                  <Badge badgeContent={value.students.length} color="primary">
                    <SchoolIcon />
                  </Badge>
                </IconButton>
              </Box>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default FloorMap;
