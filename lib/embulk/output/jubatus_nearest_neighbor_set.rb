Embulk::JavaPlugin.register_output(
  "jubatus_nearest_neighbor", "org.embulk.output.jubatus_nearest_neighbor.JubatusNearestNeighborSetOutputPlugin",
  File.expand_path('../../../../classpath', __FILE__))
