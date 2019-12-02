import React from "react";
import { createStyles, Theme, makeStyles } from "@material-ui/core/styles";
import { AppBar, CssBaseline, Toolbar, Typography } from "@material-ui/core";
import Sidebar from "../Sidebar";
import FloorMap from "../FloorMap";
import { demoClasses } from "../../types";

const drawerWidth = 300;

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      display: "flex"
    },
    appBar: {
      zIndex: theme.zIndex.drawer + 1
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

  return (
    <div>
      <CssBaseline />
      <AppBar position="fixed" className={classes.appBar}>
        <Toolbar>
          <Typography variant="h6" noWrap>
            CP Locator
          </Typography>
        </Toolbar>
      </AppBar>
      <main className={classes.content}>
        <div className={classes.toolbar} />
        <FloorMap />
      </main>
      <Sidebar drawerWidth={drawerWidth} classes={demoClasses} />
    </div>
  );
};

export default App;
