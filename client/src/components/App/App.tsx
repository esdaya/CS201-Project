import React, { useState } from "react";
import { createStyles, Theme, makeStyles } from "@material-ui/core/styles";
import {
  AppBar,
  CssBaseline,
  Toolbar,
  Typography,
  Button
} from "@material-ui/core";
import Sidebar from "../Sidebar";
import FloorMap from "../FloorMap";
import { demoClasses } from "../../types";

const drawerWidth = 300;

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      display: "flex"
    },
    title: {
      flexGrow: 1
    },
    appBar: {
      zIndex: theme.zIndex.drawer + 1
    },
    menuButton: {
      marginRight: theme.spacing(1)
    },
    drawer: {
      width: drawerWidth,
      flexShrink: 0
    },
    drawerPaper: {
      width: drawerWidth
    },
    content: {
      flexGrow: 1,
      padding: theme.spacing(3),
      marginRight: drawerWidth
    },
    toolbar: theme.mixins.toolbar
  })
);

const App: React.FC = () => {
  const classes = useStyles();
  const [classId, setClassId] = useState<number | null>(null);

  return (
    <div>
      <CssBaseline />
      <AppBar position="fixed" className={classes.appBar}>
        <Toolbar>
          <Typography className={classes.title} variant="h6" noWrap>
            CP Locator
          </Typography>
          <Button className={classes.menuButton} color="inherit">
            Login as CP
          </Button>
        </Toolbar>
      </AppBar>
      <main className={classes.content}>
        <div className={classes.toolbar} />
        <FloorMap classes={demoClasses} setClassId={setClassId} />
      </main>
      <Sidebar
        drawerWidth={drawerWidth}
        classes={demoClasses}
        classId={classId}
        setClassId={setClassId}
      />
    </div>
  );
};

export default App;
