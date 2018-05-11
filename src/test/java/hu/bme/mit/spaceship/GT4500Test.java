package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockFirstTorpedoStore;
  private TorpedoStore mockSecondTorpedoStore;

  @Before
  public void init(){
    mockFirstTorpedoStore = mock(TorpedoStore.class);
    mockSecondTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(mockFirstTorpedoStore, mockSecondTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(false);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(true);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(false);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(false);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(true);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(false);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

}
