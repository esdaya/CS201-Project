import React from "react";
import { createStyles, Theme, makeStyles } from "@material-ui/core/styles";
import Drawer from "@material-ui/core/Drawer";

interface SidebarProps {
  drawerWidth: number;
}

const useStyles = makeStyles<Theme, SidebarProps>((theme: Theme) =>
  createStyles({
    drawer: ({ drawerWidth }) => ({
      width: drawerWidth,
      flexShrink: 0
    }),
    drawerPaper: ({ drawerWidth }) => ({
      width: drawerWidth
    }),
    toolbar: theme.mixins.toolbar
  })
);

const Sidebar: React.FC<SidebarProps> = ({ drawerWidth }) => {
  const classes = useStyles({ drawerWidth });

  return (
    <aside>
      <Drawer
        className={classes.drawer}
        variant="permanent"
        anchor="right"
        classes={{
          paper: classes.drawerPaper
        }}
      >
        <div className={classes.toolbar} />
      </Drawer>
    </aside>
  );
};

export default Sidebar;
